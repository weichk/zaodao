package com.acooly.zaodao.web.manage;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.zaodao.platform.entity.ShopSupplier;
import com.acooly.zaodao.platform.service.manage.ShopSupplierService;
import com.acooly.zaodao.web.manage.common.CreditConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping(value = "/manage/credit/shopSupplier")
public class ShopSupplierManagerController extends AbstractJQueryEntityController<ShopSupplier, ShopSupplierService> {

	@Autowired
	private ShopSupplierService shopSupplierService;

	@Override
	protected ShopSupplier onSave(HttpServletRequest request, HttpServletResponse response, Model model,
                                  ShopSupplier entity, boolean isCreate) throws Exception {
		//entity.setOptUser(((User) SecurityUtils.getSubject().getPrincipal()).getUsername());
		return super.onSave(request, response, model, entity, isCreate);
	}

	@Override
	protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
		model.put("allStatuss", CreditConstants.COMMON_SWITCH_MAPPING);
	}

}
