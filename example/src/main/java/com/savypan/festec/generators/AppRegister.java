package com.savypan.festec.generators;

import com.savypan.annotations.AppRegisterGenerator;
import com.savypan.latte.wechat.template.AppRegisterTemplate;

@AppRegisterGenerator(
	packageName = "com.savypan.fastec.example",
	registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}
