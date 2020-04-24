package com.zhancheng.backstage.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.ProductService;
import com.zhancheng.backstage.service.StockHistoryService;
import com.zhancheng.backstage.service.StockService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.dao.ProductGuigeNameMapper;
import com.zhancheng.core.dao.ProductGuigeSkuMapper;
import com.zhancheng.core.dao.ProductMapper;
import com.zhancheng.core.dao.ProductTypeMapper;
import com.zhancheng.core.dto.*;
import com.zhancheng.core.entity.*;
import com.zhancheng.core.enums.StockTypeEnum;
import com.zhancheng.core.vo.SkuStockVo;
import com.zhancheng.core.vo.StockCountVo;
import com.zhancheng.core.vo.StockListVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BianShuHeng
 * @decription
 * @project StockServiceImpl
 * @date 2019/10/22 16:27
 */
@Service
public class StockServiceImpl implements StockService {

    @Resource
    private ProductMapper productMapper;

    @Resource
    private StockHistoryService stockHistoryService;

    @Resource
    private ProductGuigeSkuMapper productGuigeSkuMapper;

    @Resource
    private ProductGuigeNameMapper productGuigeNameMapper;

    @Resource
    private ProductTypeMapper productTypeMapper;

    @Override
    public IPage<StockListVo> queryStock(PageDto<Product> pageDto, StockQueryDto stockQueryDto) {
        return productMapper.queryStock(pageDto.getPage(), stockQueryDto);
    }

    @Override
    public Map<String, Object> querySkuStock(PageDto<Product> pageDto, SkuStockQueryDto skuStockQueryDto) {

        Integer pid = skuStockQueryDto.getPid();
        IPage<SkuStockVo> skuStockPage= productGuigeSkuMapper.queryStock(pageDto.getPage(), skuStockQueryDto);

        if (ObjectUtil.isEmpty(skuStockPage.getRecords())){
            return null;
        }

        Map<String, Object> map = productTypeMapper.queryType(pid);
        List<ProductGuigeName> productGuigeNameList = productGuigeNameMapper.queryList(pid);

        map.put("guigeNameList",productGuigeNameList);
        map.put("skuStock",skuStockPage);
        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean adjustStock(StockDto stockDto) {

        Integer num = stockDto.getNum();

        // 描述信息字符串
        StringBuffer stringBuffer = new StringBuffer("总部手动");

        Product product = productMapper.selectById(stockDto.getPid());
        if (ObjectUtil.isNull(product)){
            throw new MyException(CodeMsg.PRODUCT_NOT_EXISTED);
        }
        // 可用库存
        Integer availableStock = product.getAvailableStock();
        // 总库存
        Integer totalStock = product.getTotalStock();

        // 修改商品库存
        if (stockDto.getChangeType() == 1){
            availableStock += num;
            totalStock += num;
            stringBuffer.append("增加");
        }else {
            availableStock -= num;
            totalStock -= num;
            stringBuffer.append("减少");
        }
        // 添加库存记录
        stringBuffer.append(product.getProductName()).append(" 数量" + num + " ");

        if (StrUtil.isNotBlank(stockDto.getRemark())){
            stringBuffer.append("备注:" + stockDto.getRemark());
        }
        String intro = stringBuffer.toString();
        Boolean insert = addStockHistory(stockDto, product.getPid(),null, totalStock, intro, num);

        // 更新库存
        product.setAvailableStock(availableStock).setTotalStock(totalStock);
        boolean update = product.updateById();

        return update && insert;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean adjustSkuStock(StockDto stockDto) {
        Integer pid = stockDto.getPid();

        SkuStockDto[] skuStockDtos = stockDto.getSkuStockDtos();
        if (ObjectUtil.isEmpty(skuStockDtos)){
            throw new MyException(CodeMsg.PARAMETER_NULL);
        }

        Product product = productMapper.selectById(pid);
        if (ObjectUtil.isNull(product)){
            throw new MyException(CodeMsg.PRODUCT_NOT_EXISTED);
        }

        // 描述信息字符串
        StringBuffer stringBuffer = new StringBuffer("总部手动");

        if (stockDto.getChangeType() == 1){
            stringBuffer.append("增加");
        }else {
            stringBuffer.append("减少");
        }

        for (SkuStockDto skuStockDto : skuStockDtos) {

            ProductGuigeSku productGuigeSku = productGuigeSkuMapper.selectById(skuStockDto.getSkuId());

            if (ObjectUtil.isNull(productGuigeSku)){
                throw new MyException(CodeMsg.PRODUCT_SKU_NOT_EXISTED);
            }

            // 调整的数量
            Integer num = skuStockDto.getNum();
            // 可用库存
            Integer availableStock = productGuigeSku.getAvailableStock();
            // 总库存
            Integer totalStock = productGuigeSku.getTotalStock();
            // 修改商品库存
            if (stockDto.getChangeType() == 1){
                availableStock += num;
                totalStock += num;
            }else {
                availableStock -= num;
                totalStock -= num;
            }
            productGuigeSku.setAvailableStock(availableStock).setTotalStock(totalStock);
            // 添加库存记录
            String remark = product.getProductName() + productGuigeSku.getSp1() + "," + productGuigeSku.getSp2()
                    + " 数量" + num;
            stringBuffer.append(remark);

            if (StrUtil.isNotBlank(stockDto.getRemark())){
                stringBuffer.append("备注:" + stockDto.getRemark());
            }
            String intro = stringBuffer.toString();
            addStockHistory(stockDto, product.getPid(),productGuigeSku.getSkuId(), totalStock, intro, num);
        }

        // 重置商品库存数量
        StockCountVo stockCountVo = productGuigeSkuMapper.countStock(pid);
        product.setTotalStock(stockCountVo.getTotalStock());
        product.setAvailableStock(stockCountVo.getAvailableStock());
        product.setLockStock(stockCountVo.getLockStock());
        product.setExcessStock(stockCountVo.getExcessStock());
        product.setCloudStock(stockCountVo.getCloudStock());

        return  product.updateById();
    }

    @Override
    public IPage queryAgentStock(PageDto<AgentStock> pageDto, AgentQueryListDto agentQueryListDto) {
        return null;
    }

    private Boolean addStockHistory(StockDto stockDto, Integer pid, Integer skuId, Integer totalStock, String intro, Integer num){
        int type = StockTypeEnum.MANUAL_SETTING.getType();
        StockHistory stockHistory = new StockHistory();
        stockHistory.setPid(pid)
                .setSkuId(skuId)
                .setType(type)
                .setChangeType(stockDto.getChangeType())
                .setNum(num)
                .setStock(totalStock)
                .setUserType(stockDto.getUserType())
                .setOperatorId(stockDto.getOperatorId())
                .setIntro(intro);
        return stockHistoryService.insert(stockHistory);
    }
}
