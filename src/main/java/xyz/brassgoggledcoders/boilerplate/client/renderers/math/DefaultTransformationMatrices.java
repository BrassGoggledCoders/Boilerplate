package xyz.brassgoggledcoders.boilerplate.client.renderers.math;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;

// Note these are based off the Minecarts, Change when we figure out something better.
@SuppressWarnings("deprecation")
public class DefaultTransformationMatrices {
	final static TransformationMatrix transformationMatrixFirstPerson = createTransformationMatrixFirstPerson();
	final static TransformationMatrix transformationMatrixThirdPerson = createTransformationMatrixThirdPerson();
	final static TransformationMatrix transformationMatrixGui = createTransformationMatrixGui();
	final static TransformationMatrix transformationMatrixGround = createTransformationMatrixGround();
	final static TransformationMatrix transformationMatrixDefault = createTransformationMatrixDefault();

	public static TransformationMatrix getTransformMatrixForPerspective(TransformType cameraTransformsType) {
		switch(cameraTransformsType) {
			case FIRST_PERSON_LEFT_HAND:
				return transformationMatrixFirstPerson;
			case FIRST_PERSON_RIGHT_HAND:
				return transformationMatrixFirstPerson;
			case THIRD_PERSON_LEFT_HAND:
				return transformationMatrixThirdPerson;
			case THIRD_PERSON_RIGHT_HAND:
				return transformationMatrixThirdPerson;
			case GUI:
				return transformationMatrixGui;
			case GROUND:
				return transformationMatrixGround;
			default:
				return transformationMatrixDefault;
		}
	}

	public static TransformationMatrix createTransformationMatrixFirstPerson() {
		return new TransformationMatrix(0.25, -0.275, 0.35).multiplyRightWith(new TransformationMatrix(90, 1, 0, 0))
				.multiplyRightWith(new TransformationMatrix(225, 0, 0, 1))
				.multiplyRightWith(new TransformationMatrix(180, 0, 1, 0)).scale(0.075, 0.075, 0.075);
	}

	public static TransformationMatrix createTransformationMatrixThirdPerson() {
		return new TransformationMatrix(0, 0.1, -0.07).multiplyRightWith(new TransformationMatrix(180, 0, 1, 0))
				.scale(0.02, 0.02, 0.02);
	}

	public static TransformationMatrix createTransformationMatrixGui() {
		return new TransformationMatrix(0.025, 0, 0).multiplyRightWith(new TransformationMatrix(90, -1, 0, 1))
				.multiplyRightWith(new TransformationMatrix(90, 0, 0, 1))
				.multiplyRightWith(new TransformationMatrix(75, 0, 1, 0))
				.multiplyRightWith(new TransformationMatrix(-90, 0, 1, 0)).scale(0.03, 0.03, 0.03);
	}

	public static TransformationMatrix createTransformationMatrixGround() {
		return new TransformationMatrix(-0.2, 0, 0.05).multiplyRightWith(new TransformationMatrix(90, 1, 0, 0))
				.multiplyRightWith(new TransformationMatrix(90, 0, 0, 1))
				.multiplyRightWith(new TransformationMatrix(180, 0, 1, 0)).scale(0.03, 0.03, 0.03);
	}

	public static TransformationMatrix createTransformationMatrixDefault() {
		return new TransformationMatrix(0.25, -0.275, 0.35).multiplyRightWith(new TransformationMatrix(90, 1, 0, 0))
				.multiplyRightWith(new TransformationMatrix(225, 0, 0, 1))
				.multiplyRightWith(new TransformationMatrix(180, 0, 1, 0)).scale(0.075, 0.075, 0.075);
	}
}
