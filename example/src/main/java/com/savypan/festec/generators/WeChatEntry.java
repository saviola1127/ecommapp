package com.savypan.festec.generators;

import com.savypan.annotations.EntryGenerator;
import com.savypan.latte.wechat.template.WXEntryTemplate;

@EntryGenerator(
	packageName = "com.savypan.fastec.example",
	entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}
