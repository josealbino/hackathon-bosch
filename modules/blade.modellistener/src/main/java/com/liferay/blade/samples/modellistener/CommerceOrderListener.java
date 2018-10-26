/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.blade.samples.modellistener;

import com.liferay.commerce.model.CommerceOrder;

import javax.mail.internet.InternetAddress;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;

import com.liferay.portal.kernel.model.ModelListener;

import org.osgi.service.component.annotations.Component;

/**
 * @author Liferay
 */
@Component(immediate = true, service = ModelListener.class)
public class CommerceOrderListener extends BaseModelListener<CommerceOrder> {

	@Override
	public void onBeforeCreate(CommerceOrder model) throws ModelListenerException {
		System.out.println(
			"About to create order: " + model.getPurchaseOrderNumber());

			sendMailWithPlainText("bosh@mail.com", "client@mail.com");

	}

	public void sendMailWithPlainText(String fromAddress, String toAddress) {

		try {
			MailMessage mailMessage = new MailMessage();
			mailMessage.setTo(new InternetAddress(toAddress));
			mailMessage.setFrom(new InternetAddress(fromAddress));
			mailMessage.setSubject("We recieved your order!");
			mailMessage.setBody("We are happy to work on this new order, Thank you!");
			MailServiceUtil.sendEmail(mailMessage);

			System.out.println("Mail sent ...");
		} catch (Exception e) {
				e.printStackTrace();
		}
	}

}