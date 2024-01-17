package blueduck.outer_end.client.model;// Made with Blockbench 4.9.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


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
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class PurpurGolemModel<T extends PurpurGolem> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("outer_end", "purpurgolemmodel"), "main");
	private final ModelPart hips;

	public PurpurGolemModel(ModelPart root) {
		this.hips = root.getChild("hips");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition hips = partdefinition.addOrReplaceChild("hips", CubeListBuilder.create().texOffs(0, 23).addBox(-6.0F, -5.0F, -3.0F, 12.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 0.0F));

		PartDefinition chest = hips.addOrReplaceChild("chest", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, -11.6667F, -4.1667F, 20.0F, 12.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.3333F, 0.1667F));

		PartDefinition rightarm = chest.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(56, 15).addBox(-7.0F, 4.0F, -4.0F, 6.0F, 17.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(30, 31).addBox(-8.0F, -4.0F, -5.0F, 8.0F, 8.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 105).addBox(-8.0F, -9.0F, -5.0F, 8.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-10.0F, -8.6667F, 0.8333F));

		PartDefinition cube_r1 = rightarm.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(16, 95).addBox(0.0F, -2.5F, -5.0F, 0.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, -6.5F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition leftarm = chest.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(0, 59).addBox(1.0F, 4.0F, -4.0F, 6.0F, 17.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 41).addBox(0.0F, -4.0F, -5.0F, 8.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(10.0F, -8.6667F, 0.8333F));

		PartDefinition cube_r2 = leftarm.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(16, 95).addBox(0.0F, -2.5F, -5.0F, 0.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -6.5F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r3 = leftarm.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 105).addBox(-4.0F, -5.0F, 0.0F, 8.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -4.0F, -5.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition head = chest.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 97).addBox(-3.0F, -10.0F, -5.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(36, 49).addBox(-5.0F, -12.0F, -4.0F, 10.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.6667F, 0.8333F));

		PartDefinition leftleg = hips.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(64, 64).addBox(-3.0F, 2.0F, -4.0F, 6.0F, 16.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 3.0F, 1.0F));

		PartDefinition rightleg = hips.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(28, 67).addBox(-3.0F, 2.0F, -4.0F, 6.0F, 16.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 3.0F, 1.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

		hips.getChild("rightleg").xRot = (float) (Math.sin(limbSwing/2) * limbSwingAmount);
		hips.getChild("leftleg").xRot = -hips.getChild("rightleg").xRot;
		hips.getChild("chest").getChild("rightarm").xRot = (float) (Math.sin(limbSwing/2) * limbSwingAmount);
		hips.getChild("chest").getChild("leftarm").xRot = -hips.getChild("chest").getChild("rightarm").xRot;
		if (entity.getAttackAnim(1) != 0) {
			if (entity.getAttackAnim(1) <= 1) {
				float amt = entity.getAttackAnim(1) + (Minecraft.getInstance().getPartialTick()*0.15f);
				hips.getChild("chest").getChild("rightarm").xRot = (float)Math.toRadians(-(amt*180));
			} else {
				float amt = -(entity.getAttackAnim(1) + (Minecraft.getInstance().getPartialTick()*0.15f) - 0.15f);
				hips.getChild("chest").getChild("rightarm").xRot = (float)Math.toRadians(-(amt*180));
			}
			hips.getChild("chest").getChild("rightarm").xRot += Math.toRadians(0.1f*180);
			hips.getChild("chest").getChild("leftarm").xRot = hips.getChild("chest").getChild("rightarm").xRot;
		} else {
			hips.getChild("chest").getChild("leftarm").xRot = hips.getChild("rightleg").xRot;
			hips.getChild("chest").getChild("rightarm").xRot = -hips.getChild("chest").getChild("leftarm").xRot;
		}
		this.hips.getChild("chest").getChild("head").yRot = (float) Math.toRadians(netHeadYaw);

	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		hips.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}