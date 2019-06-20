package com.acooly.zaodao.web.integralshopping;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.web.support.JsonResult;
import com.acooly.core.utils.Strings;
import com.acooly.module.cms.domain.Content;
import com.acooly.module.cms.service.ContentService;
import com.acooly.module.ofile.OFileProperties;
import com.acooly.module.point.domain.PointTrade;
import com.acooly.module.portlet.entity.SiteConfig;
import com.acooly.module.portlet.enums.SiteConfigKeyEnum;
import com.acooly.module.portlet.service.SiteConfigService;
import com.acooly.zaodao.SysConstant;
import com.acooly.zaodao.common.AbstractPortalController;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.entity.ShopGoods;
import com.acooly.zaodao.platform.entity.ShopGoodsDetail;
import com.acooly.zaodao.platform.entity.ShopGoodsType;
import com.acooly.zaodao.platform.service.manage.ShopGoodsDetailService;
import com.acooly.zaodao.platform.service.manage.ShopGoodsService;
import com.acooly.zaodao.platform.service.manage.ShopGoodsTypeService;
import com.acooly.zaodao.platform.service.platform.PointAmountService;
import com.acooly.zaodao.platform.service.platform.PointTradeInfoService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/portal/shop")
public class ShopPortalController extends AbstractPortalController<ShopGoods, ShopGoodsService> {

    @Autowired
    private ShopGoodsService shopGoodsService;

    @Autowired
    private ShopGoodsTypeService shopGoodsTypeService;

    @Autowired
    private ContentService contentService;

    @Autowired
    private OFileProperties oFileProperties;

    @Autowired
    private ShopGoodsDetailService shopGoodsDetailService;

    @Autowired
    private PointTradeInfoService pointTradeInfoService;

    @Autowired
    private PointAmountService pointAmountService;

    @Autowired
    private SiteConfigService siteConfigService;

    /**
     * 右侧漂浮工具
     */
    @RequestMapping("portlet/rigthTool")
    public String rigthTool(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<SiteConfig> siteConfigs = siteConfigService.getAll();
        List<String> serviceQQs = Lists.newArrayList();
        String qqGroup = "";
        for(SiteConfig siteConfig:siteConfigs) {
            if(siteConfig.getName().startsWith("site_qq")) {
                serviceQQs.add(siteConfig.getValue());
            }
            if(Strings.equals("site_service_qq_group",siteConfig.getName())) {
                qqGroup = siteConfig.getValue();
            }
        }
        //服务QQ
        model.addAttribute("serviceQQs",serviceQQs);
        //服务QQ群
        model.addAttribute("qqGroup",qqGroup);
        return "/portal/integralshopping/right_tool";
    }

    /**
     * 横幅
     */
    @RequestMapping("portlet/integralBanner")
    public String integralBanner(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            //获取横幅
            int count = getTopCount(request);
            List<Content> contents = contentService.topByTypeCode("pointShopBanner", count);
            model.addAttribute("pointShopBanner", contents);
            model.addAttribute("count", count);
        } catch (Exception e) {
            handleException("积分商城横幅", e, request);
        }

        return "/portal/portlet/integral_banner";
    }

    /**
     * 积分商城
     */
    @RequestMapping("integralShopping")
    public String integralShopping(HttpServletRequest request, HttpServletResponse response, Model model) {
        //获取文件读取虚拟路径
        model.addAttribute("mediaRoot", oFileProperties.getServerRoot());
        //获取热门商品
        List<ShopGoods> hotShopGoods = shopGoodsService.findHotGoods(4);
        model.addAttribute("hotShopGoods", hotShopGoods);
        //获取商品类型
        List<ShopGoodsType> shopGoodsTypes = shopGoodsTypeService.loadTree(null);
        for (ShopGoodsType shopGoodsType : shopGoodsTypes) {
            List<ShopGoods> shopGoods = shopGoodsService.findByTypeCodeStartWith(shopGoodsType.getCode(), 8);
            shopGoodsType.setShopGoodsList(shopGoods);
        }
        model.addAttribute("shopGoodsTypes", shopGoodsTypes);
        return "/portal/integralshopping/index";
    }

    /**
     * 获取商品详情
     *
     * @param id
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("detail_{id}")
    public String detail(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response,
                         Model model) {
        try {
            model.addAllAttributes(referenceData(request));
            Customer customer = (Customer) (request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO));
            if (customer != null) {
//				model.addAttribute("creditAccount", getCreditAccount(request));
                model.addAttribute("customer", customerService.get(customer.getId()));
            }
            ShopGoods shopGoods = getEntityService().get(id);
            ShopGoodsDetail shopGoodsDetail = shopGoodsDetailService.getEntityByGoodsId(shopGoods.getId());
            shopGoods.setShopGoodsDetail(shopGoodsDetail);
            model.addAttribute("shopGoods", shopGoods);
            model.addAttribute("pageType", "shop");
        } catch (Exception e) {
            handleException("商品详情", e, request);
        }
        return "/portal/integralshopping/goods_detail";
    }

    @RequestMapping("/exchangeGoods")
    public String exchangeGoods(Long goodsId, Integer quantity, HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
//			sendCSRFToken(request);
//			model.addAttribute("pointAccount", getPointAccount(request));
            model.addAllAttributes(referenceData(request));
            ShopGoods shopGoods = getEntityService().get(Long.valueOf(goodsId));
            model.addAttribute("shopGoods", shopGoods);
            model.addAttribute("quantity", quantity);
            Customer customer = loadCustomer(request);
            model.addAttribute("mobileNo", customer.getMobileNo());
            model.addAttribute("realName", customer.getRealName());
//			model.addAttribute("provName", customer.getCustomerContact().getLiveProvince());
//			model.addAttribute("cityName", customer.get);
//			model.addAttribute("distName", customer.getCustomerContact().getLiveCounty());
            model.addAttribute("address", customer.getContactAddress());
        } catch (Exception e) {
            handleException("商品订单", e, request);
        }
        return "/portal/integralshopping/exchange_goods";
    }

    @RequestMapping("/goodsList")
    public String goodsList(HttpServletRequest request, HttpServletResponse response, Model model) {
//		getEntityService().getPageShopGoodsByGoodTypeCode(currentPageNo,countOfCurrentPage,goodsTypeCode);
//		model.addAttribute("mainCode",mainCode);
        String numStr = request.getParameter("num");
        String mainCode = request.getParameter("mainCode");
        int countOfCurrentPage = 12;
        Map<String, Object> searchParams = getSearchParams(request);
        try {
            model.addAllAttributes(referenceData(request));
            if (StringUtils.isNotBlank(numStr)) {
                countOfCurrentPage = Integer.parseInt(numStr);
            }
            String currentPage = request.getParameter("currentPage");
            PageInfo<ShopGoods> pageInfo = new PageInfo<>();
            pageInfo.setCountOfCurrentPage(countOfCurrentPage);
            if (StringUtils.isNotBlank(currentPage)) {
                pageInfo.setCurrentPage(Integer.parseInt(currentPage));
            }

            Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
            PageInfo<ShopGoods> pageInfo_ = shopGoodsService.query(pageInfo, searchParams, sortMap);
            model.addAttribute("shopGoodsPage", pageInfo_);
            model.addAttribute("mainCode", mainCode);
        } catch (Exception e) {
            handleException("商品列表", e, request);
        }
        if (StringUtils.isBlank(numStr)) {
            // 分页
            Object rObject = searchParams.get("RLIKE_shopGoodsType.code");
            Object lObject = searchParams.get("LLIKE_shopGoodsType.code");
            if (rObject != null) {
                model.addAttribute("rShopGoodsType", String.valueOf(rObject));
            }
            if (lObject != null) {
                model.addAttribute("lShopGoodsType", String.valueOf(lObject));
            }
            return "/portal/integralshopping/goods_list";
        }
        if (StringUtils.isNotBlank(request.getParameter("search_EQ_isHot"))) {
            // 热门推荐
            return "/portal/point/goods_hot";
        }
        return "/portal/integralshopping/goods_list";
    }

    /**
     * 商品列表查询
     */
    @Override
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
        super.list(request, response, model);
        return "/portal/credit/index";
    }

    @Override
    protected Map<String, Object> getSearchParams(HttpServletRequest request) {
        Map<String, Object> map = super.getSearchParams(request);
        map.put("EQ_status", ShopGoods.STATUS_SALES);
        return map;
    }

    /**
     * 商品分类
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("type")
    public String type(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            model.addAllAttributes(referenceData(request));
            List<ShopGoodsType> shopGoodsTypes = shopGoodsTypeService.loadTree(null);
            model.addAttribute("shopGoodsTypes", shopGoodsTypes);
        } catch (Exception e) {
            handleException("商品分类", e, request);
        }
        return "/portal/credit/goods_type";
    }

    /**
     * 积分兑换商品
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("paymentAction")
    @ResponseBody
    public JsonResult paymentAction(HttpServletRequest request, HttpServletResponse response, Model model) {
        JsonResult result = new JsonResult();
        try {
            validateCaptcha(request);
            validatePassword(request);
            String goodsId = request.getParameter("goodsId");
            String quantity = request.getParameter("quantity");
            String provName = request.getParameter("provName");
            String cityName = request.getParameter("cityName");
            String distName = request.getParameter("distName");
            String address = request.getParameter("address");
            String comments = request.getParameter("comments");
            String realName = request.getParameter("realName");
            String mobileNo = request.getParameter("mobileNo");
            ShopGoods goods = shopGoodsService.get(Long.valueOf(goodsId));
            Customer customer = loadCustomer(request);
            if (goods.getMaxBuyNum() != 0) {
                List<PointTrade> list = pointTradeInfoService.findByUserIdAndGoodsId(customer.getUserId(),
                        goods.getId() + "");
                if ((list.size() + Integer.parseInt(quantity)) > goods.getMaxBuyNum()) {
                    throw new RuntimeException("大于最高限购数量");
                }
            }
            pointAmountService.pointPayment(goodsId, quantity, provName, cityName, distName, address, comments,
                    realName, mobileNo, customer);
            model.addAllAttributes(referenceData(request));
        } catch (Exception e) {
            handleAjaxException("商品兑换", e, result);
        }
        return result;
    }

    @Override
    protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
    }

    @Override
    public int getDefaultPageSize() {
        return 16;
    }
}
