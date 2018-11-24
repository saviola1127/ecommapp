package com.savypan.compiler;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;
import java.io.IOException;

public final class EntryVisitor extends SimpleAnnotationValueVisitor7<Void, Void> {
	private Filer mFiler = null;
	private String mPackageName = null;
	private TypeMirror mTypeMirror = null;

	public void setFiler(Filer mFiler) {
		this.mFiler = mFiler;
	}

	@Override
	public Void visitString(String s, Void aVoid) {
		mPackageName = s;
		return aVoid;
	}

	@Override
	public Void visitType(TypeMirror typeMirror, Void p) {
		mTypeMirror = typeMirror;
		generateJavacode();
		return p;
	}

	private void generateJavacode() {
		final TypeSpec targetActivity = TypeSpec.classBuilder("WXEntryActivity")
			.addModifiers(Modifier.PUBLIC)
			.addModifiers(Modifier.FINAL)
			.superclass(TypeName.get(mTypeMirror))
			.build();

		final JavaFile javaFile = JavaFile.builder(mPackageName+".wxapi", targetActivity)
			.addFileComment("微信入口文件")
			.build();

		try {
			javaFile.writeTo(mFiler);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
