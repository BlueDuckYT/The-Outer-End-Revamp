package blueduck.outer_end.client.model;// Made with Blockbench 4.9.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import blueduck.outer_end.entity.Entombed;
import blueduck.outer_end.entity.PurpurGolem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
public class EntombedModel<T extends Entombed> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("outer_end", "entombedmodel"), "main");
	private final ModelPart Torso;

	public EntombedModel(ModelPart root) {
		this.Torso = root.getChild("Torso");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Torso = partdefinition.addOrReplaceChild("Torso", CubeListBuilder.create().texOffs(0, 0).addBox(-11.0F, -38.0F, -5.0F, 22.0F, 31.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition RightLeg = Torso.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(36, 41).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.5F, -7.0F, 0.0F));

		PartDefinition RightArm = Torso.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(12, 41).addBox(-2.0F, -1.0F, -1.5F, 3.0F, 18.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-12.0F, -19.0F, 0.0F));

		PartDefinition LeftLeg = Torso.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(24, 41).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(6.5F, -7.0F, 0.0F));

		PartDefinition LeftArm = Torso.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(0, 41).addBox(-1.0F, -1.0F, -1.5F, 3.0F, 18.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(12.0F, -19.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		Torso.getChild("RightLeg").xRot = (float) (Math.sin(limbSwing/2) * limbSwingAmount);
		Torso.getChild("LeftLeg").xRot = (float) -(Math.sin(limbSwing/2) * limbSwingAmount);
		Torso.getChild("RightArm").xRot = (float) (Math.sin(limbSwing/2) * limbSwingAmount);
		Torso.getChild("LeftArm").xRot = (float) -(Math.sin(limbSwing/2) * limbSwingAmount);
		if (entity.attackAnim != 0) {
			if (entity.attackAnim <= 1) {
				float amt = entity.attackAnim + (Minecraft.getInstance().getPartialTick() * 0.15f);
				Torso.getChild("RightArm").xRot = (float) Math.toRadians(-(amt * 180));
				Torso.getChild("LeftArm").xRot = (float) Math.toRadians(-(amt * 180));
			} else {
				float amt = -(entity.attackAnim + (Minecraft.getInstance().getPartialTick() * 0.15f) - 0.15f);
				Torso.getChild("RightArm").xRot = (float) Math.toRadians(-(amt * 180));
				Torso.getChild("LeftArm").xRot = (float) Math.toRadians(-(amt * 180));
			}
			Torso.getChild("RightArm").xRot += Math.toRadians(0.1f * 180);
			Torso.getChild("LeftArm").xRot += Math.toRadians(0.1f * 180);
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Torso.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}