package blueduck.outer_end.client.model;// Made with Blockbench 4.9.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import blueduck.outer_end.entity.Himmelite;
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
public class HimmeliteModel<T extends Himmelite> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "himmelitemodel"), "main");
	private final ModelPart body;

	public HimmeliteModel(ModelPart root) {
		this.body = root.getChild("body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 2.0F));

		PartDefinition hips = body.addOrReplaceChild("hips", CubeListBuilder.create().texOffs(0, 26).addBox(-4.0F, -7.0F, -7.0F, 8.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition frontleftleg = hips.addOrReplaceChild("frontleftleg", CubeListBuilder.create().texOffs(0, 40).addBox(-1.0F, 1.0F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -5.0F, -4.5F));

		PartDefinition backleftleg = hips.addOrReplaceChild("backleftleg", CubeListBuilder.create().texOffs(0, 40).addBox(-1.0F, 1.0F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -5.0F, 0.5F));

		PartDefinition frontrightleg = hips.addOrReplaceChild("frontrightleg", CubeListBuilder.create().texOffs(0, 40).addBox(-1.0F, 1.0F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -5.0F, -4.5F));

		PartDefinition backrightleg = hips.addOrReplaceChild("backrightleg", CubeListBuilder.create().texOffs(0, 40).addBox(-1.0F, 1.0F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -5.0F, 0.5F));

		PartDefinition head = hips.addOrReplaceChild("head", CubeListBuilder.create().texOffs(25, 26).addBox(-5.0F, -7.0F, 0.0F, 10.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, -4.0F));

		PartDefinition upjaw = head.addOrReplaceChild("upjaw", CubeListBuilder.create().texOffs(31, 0).addBox(-5.0F, 0.0F, -8.0F, 0.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-5.0F, -2.0F, -8.0F, 10.0F, 2.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(31, 0).addBox(5.0F, 0.0F, -8.0F, 0.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, 0.0F));

		PartDefinition cube_r1 = upjaw.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 37).addBox(0.0F, -1.0F, -6.0F, 0.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 1.0F, -8.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition downjaw = head.addOrReplaceChild("downjaw", CubeListBuilder.create().texOffs(31, 6).addBox(-5.0F, -3.0F, -8.0F, 0.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 13).addBox(-5.0F, 0.0F, -8.0F, 10.0F, 2.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(31, 6).addBox(5.0F, -3.0F, -8.0F, 0.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 0.0F));

		PartDefinition cube_r2 = downjaw.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 40).addBox(0.0F, -2.0F, -6.0F, 0.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.0F, -8.0F, 0.0F, -1.5708F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		body.getChild("hips").getChild("frontrightleg").xRot = (float) (Math.sin(limbSwing/2) * limbSwingAmount);
		body.getChild("hips").getChild("frontleftleg").xRot = -body.getChild("hips").getChild("frontrightleg").xRot;
		body.getChild("hips").getChild("backrightleg").xRot = -body.getChild("hips").getChild("frontrightleg").xRot;
		body.getChild("hips").getChild("backleftleg").xRot = body.getChild("hips").getChild("frontrightleg").xRot;

		body.getChild("hips").getChild("head").getChild("upjaw").xRot = (float) ((float) (-Math.abs(Math.sin(limbSwing / 3)) * limbSwingAmount / 8f) - Math.toRadians(30));
		body.getChild("hips").getChild("head").getChild("downjaw").xRot = (float) (-body.getChild("hips").getChild("head").getChild("upjaw").xRot - Math.toRadians(30));

		if (entity.attackAnim != 0) {
			if (entity.attackAnim <= 1) {
				float amt = entity.attackAnim + (Minecraft.getInstance().getPartialTick() * 0.15f);
				body.getChild("hips").getChild("head").getChild("upjaw").xRot = (float)Math.toRadians(-(90 - amt*90));
				body.getChild("hips").getChild("head").getChild("downjaw").xRot = (float)Math.toRadians(-(amt*45 - 30));

			}
			else {
				float amt = -(entity.attackAnim + (Minecraft.getInstance().getPartialTick()*0.15f) - 0.15f);
				body.getChild("hips").getChild("head").getChild("upjaw").xRot = (float)Math.toRadians(-(90 - amt*90));
				body.getChild("hips").getChild("head").getChild("downjaw").xRot = (float)Math.toRadians(-(amt*45 - 30));

			}
		}

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}