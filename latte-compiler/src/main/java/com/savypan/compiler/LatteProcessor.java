package com.savypan.compiler;

import com.savypan.annotations.AppRegisterGenerator;
import com.savypan.annotations.EntryGenerator;
import com.savypan.annotations.PayEntryGenerator;
import com.google.auto.service.AutoService;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.*;
import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("unused")
@AutoService(Processor.class)
public class LatteProcessor extends AbstractProcessor {
	@Override
	public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
		generateEntryCode(roundEnvironment);
		generatePayEntryCode(roundEnvironment);
		generateAppRegisterCode(roundEnvironment);
		return true;
	}

	@Override
	public Set<String> getSupportedAnnotationTypes() {
		final Set<String> types = new LinkedHashSet<>();
		final Set<Class<? extends Annotation>> supportAnnotations = getSupportedAnnotations();
		for (Class<? extends Annotation> annotation : supportAnnotations) {
			types.add(annotation.getCanonicalName());
		}
		return types;
	}

	private Set<Class<? extends Annotation>> getSupportedAnnotations() {
		final Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
		annotations.add(EntryGenerator.class);
		annotations.add(PayEntryGenerator.class);
		annotations.add(AppRegisterGenerator.class);
		return annotations;
	}

	private void scan(RoundEnvironment env, Class<? extends Annotation> annotation,
		AnnotationValueVisitor visitor) {
		for (Element typeElement : env.getElementsAnnotatedWith(annotation)) {
			final List<? extends AnnotationMirror> annotationMirrors =
				typeElement.getAnnotationMirrors();

			for (AnnotationMirror annotationMirror:annotationMirrors) {
				final Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues =
					annotationMirror.getElementValues();

				for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry
					:elementValues.entrySet()) {
					entry.getValue().accept(visitor, null);
				}
			}
		}
	}

	private void generateEntryCode(RoundEnvironment env) {
		final EntryVisitor entryVisitor = new EntryVisitor();
		entryVisitor.setFiler(processingEnv.getFiler());
		scan(env, EntryGenerator.class, entryVisitor);
	}

	private void generatePayEntryCode(RoundEnvironment env) {
		final PayEntryVisitor entryVisitor = new PayEntryVisitor();
		entryVisitor.setFiler(processingEnv.getFiler());
		scan(env, EntryGenerator.class, entryVisitor);
	}

	private void generateAppRegisterCode(RoundEnvironment env) {
		final AppRegisterVisitor entryVisitor = new AppRegisterVisitor();
		entryVisitor.setFiler(processingEnv.getFiler());
		scan(env, EntryGenerator.class, entryVisitor);
	}
}
