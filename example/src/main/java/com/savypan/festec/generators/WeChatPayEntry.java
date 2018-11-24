package com.savypan.festec.generators;

import com.savypan.annotations.PayEntryGenerator;
import com.savypan.latte.wechat.template.WXPayEntryTemplate;

@PayEntryGenerator(
	packageName = "com.savypan.fastec.example",
	payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
