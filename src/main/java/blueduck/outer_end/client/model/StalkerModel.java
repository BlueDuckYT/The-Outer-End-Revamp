package blueduck.outer_end.client.model;// Made with Blockbench 4.9.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
import blueduck.outer_end.entity.PurpurGolem;
import blueduck.outer_end.entity.Stalker;
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

public class StalkerModel<T extends Stalker> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("outer_end", "stalkermodel"), "main");
	private final ModelPart rearFootLeft;
	private final ModelPart rearFootRight;
	private final ModelPart haunchLeft;
	private final ModelPart haunchRight;
	private final ModelPart body;
	private final ModelPart frontLegLeft;
	private final ModelPart frontLegRight;
	private final ModelPart head;
	private final ModelPart tail;
	private final ModelPart getHead;

	public StalkerModel(ModelPart root) {
		this.rearFootLeft = root.getChild("rearFootLeft");
		this.rearFootRight = root.getChild("rearFootRight");
		this.haunchLeft = root.getChild("haunchLeft");
		this.haunchRight = root.getChild("haunchRight");
		this.body = root.getChild("body");
		this.frontLegLeft = root.getChild("frontLegLeft");
		this.frontLegRight = root.getChild("frontLegRight");
		this.head = root.getChild("head");
		this.tail = root.getChild("tail");
		this.getHead = root.getChild("getHead");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition rearFootLeft = partdefinition.addOrReplaceChild("rearFootLeft", CubeListBuilder.create(), PartPose.offset(-3.0F, 17.5F, 3.7F));

		PartDefinition rearFootRight = partdefinition.addOrReplaceChild("rearFootRight", CubeListBuilder.create(), PartPose.offset(3.0F, 17.5F, 3.7F));

		PartDefinition haunchLeft = partdefinition.addOrReplaceChild("haunchLeft", CubeListBuilder.create().texOffs(0, 26).addBox(-1.75F, -2.2576F, -4.5F, 3.0F, 12.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(79, 61).addBox(-0.75F, 9.7424F, -0.5F, 2.0F, 17.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.25F, -3.2424F, 3.2F));

		PartDefinition haunchRight = partdefinition.addOrReplaceChild("haunchRight", CubeListBuilder.create().texOffs(0, 26).addBox(-1.25F, -2.25F, -4.5F, 3.0F, 12.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(79, 61).addBox(-1.25F, 9.75F, -0.5F, 2.0F, 17.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(4.25F, -3.2424F, 2.851F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -5.637F, -24.1599F, 7.0F, 6.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(0, 26).addBox(-1.0F, -6.5762F, -24.8545F, 1.0F, 2.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 6.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition frontLegLeft = partdefinition.addOrReplaceChild("frontLegLeft", CubeListBuilder.create().texOffs(0, 26).addBox(-3.25F, -2.0F, -2.5F, 3.0F, 14.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.75F, -5.0F, -12.5F));

		PartDefinition bone4 = frontLegLeft.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(80, 61).addBox(-1.0F, 0.5F, -2.0F, 2.0F, 17.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.75F, 11.5F, 1.5F));

		PartDefinition frontLegRight = partdefinition.addOrReplaceChild("frontLegRight", CubeListBuilder.create().texOffs(1, 26).addBox(1.0F, -1.6947F, -2.4933F, 3.0F, 14.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -5.0F, -12.5F));

		PartDefinition bone = frontLegRight.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(80, 61).addBox(-1.0F, 0.5F, -2.0F, 2.0F, 17.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, 11.8053F, 1.5067F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(25, 26).addBox(-2.0F, -5.0F, -4.8333F, 4.0F, 9.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.0F, -3.0F, -5.9333F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -7.0F, -17.1667F));

		PartDefinition bone2 = head.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(45, 18).addBox(-1.0F, -1.0F, -1.5F, 2.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, -1.3333F, 0.1309F, 0.0F, -0.0436F));

		PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(29, 32).addBox(-1.6667F, -0.8172F, -1.5857F, 3.0F, 5.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(34, 0).addBox(-0.6667F, -1.3681F, -1.3432F, 1.0F, 1.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3333F, -3.9352F, 3.0711F, -0.48F, 0.0F, 0.0F));

		PartDefinition bone3 = tail.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(0, 49).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 4.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1667F, 1.6021F, 15.1508F, -0.1309F, 0.0F, 0.0F));

		PartDefinition getHead = partdefinition.addOrReplaceChild("getHead", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.frontLegRight.xRot = (float) (Math.sin(limbSwing / 2f) * (limbSwingAmount / 2f));
		//this.frontLegRight.xRot += (float) (Math.sin(ageInTicks / 128f) * ((1 - limbSwingAmount) / 64f));
		this.frontLegLeft.xRot = -frontLegRight.xRot;
		this.haunchRight.xRot = -frontLegRight.xRot;
		this.haunchLeft.xRot = frontLegRight.xRot;

		this.tail.xRot = (float) (Math.toRadians(-27.5f) + (Math.sin(ageInTicks / 16f) / 32f));
		this.tail.xRot += (float) (Math.sin(limbSwing / 4f) * ((limbSwingAmount) / 16f));

		this.tail.getChild("bone3").xRot = (float) (tail.xRot - (Math.toRadians(-20.5f))) * 2;

		this.head.xRot = (float) Math.toRadians(headPitch + 12);
		this.head.yRot = (float) Math.toRadians(netHeadYaw);

		float wanted;
		if (entity.isAngry()) {
			wanted = (float) (Math.cos(ageInTicks / 24f) * Math.toRadians(10) + Math.toRadians(70)) - head.xRot;
		}
		else {
			wanted = (float) (Math.cos(ageInTicks / 32f) * 0.25f + Math.toRadians(15)) - head.xRot;
		}

		float delta = wanted - this.head.getChild("bone2").xRot;
		if (Math.abs(delta) < Math.toRadians(2f)) {
			this.head.getChild("bone2").xRot = wanted;
		}
		else {
			float damp = 0.5f;
			delta = Math.min(delta * damp, (float) Math.toRadians(0.5f));
			this.head.getChild("bone2").xRot += delta;
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		rearFootLeft.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rearFootRight.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		haunchLeft.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		haunchRight.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		frontLegLeft.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		frontLegRight.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		getHead.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}