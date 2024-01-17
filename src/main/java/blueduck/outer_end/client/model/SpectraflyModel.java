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
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class SpectraflyModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "dragonflyentitymodel"), "main");
	private final ModelPart body;

	public SpectraflyModel(ModelPart root) {
		super(RenderType::entityTranslucent);
		this.body = root.getChild("body");
	}
	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.FLYING_SPEED, (double)0.6F).add(Attributes.MOVEMENT_SPEED, (double)0.3F).add(Attributes.ATTACK_DAMAGE, 2.0D).add(Attributes.FOLLOW_RANGE, 48.0D);
	}
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition solid = body.addOrReplaceChild("solid", CubeListBuilder.create().texOffs(0, 0).addBox(1.0F, -8.0F, -7.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).mirror().addBox(-4.0F, -8.0F, -7.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition translucent = body.addOrReplaceChild("translucent", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -10.5F, -8.0F, 9.0F, 9.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail = translucent.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(26, 34).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 8.0F));

		PartDefinition emissive = body.addOrReplaceChild("emissive", CubeListBuilder.create().texOffs(0, 26).addBox(-3.0F, -9.0F, -6.0F, 6.0F, 6.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition wing1 = body.addOrReplaceChild("wing1", CubeListBuilder.create().texOffs(30, 0).mirror().addBox(0.0F, 0.0F, -3.0F, 10.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, -10.0F, -3.0F));

		PartDefinition wing3 = body.addOrReplaceChild("wing3", CubeListBuilder.create().texOffs(30, 0).addBox(-10.0F, 0.0F, -3.0F, 10.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -10.0F, -3.0F));

		PartDefinition wing2 = body.addOrReplaceChild("wing2", CubeListBuilder.create().texOffs(30, 0).mirror().addBox(0.0F, 0.0F, -3.0F, 10.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, -10.0F, 4.0F));

		PartDefinition wing4 = body.addOrReplaceChild("wing4", CubeListBuilder.create().texOffs(30, 0).addBox(-10.0F, 0.0F, -3.0F, 10.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -10.0F, 4.0F));

		return LayerDefinition.create(meshdefinition, 80, 80);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		if (!entity.onGround()) body.xRot = (float)(entity.getDeltaMovement().y)*-0.75f;

		body.getChild("translucent").getChild("tail").setRotation(0.0F, -7.0F, 8.0F);
		if (entity.onGround()) {
			body.xRot = 0;
			if (entity.getBlockStateOn().isAir()) {
				body.getChild("wing1").zRot = (float)(Math.cos((ageInTicks*0.5f)*0.5f));
				body.getChild("wing2").zRot = (float)(Math.cos(0.2+(ageInTicks*0.5f)*0.5f));
				body.getChild("wing3").zRot = (float)(-Math.cos((ageInTicks*0.5f)*0.5f));
				body.getChild("wing4").zRot = (float)(-Math.cos(0.2+(ageInTicks*0.5f)*0.5f));
				body.getChild("translucent").getChild("tail").xRot = (float)(Math.cos((ageInTicks)*0.05f)*0.1f)-(float)Math.toRadians(15);
			} else {
				body.getChild("wing1").zRot = (float)Math.toRadians(15);
				body.getChild("wing2").zRot = (float)Math.toRadians(15);
				body.getChild("wing3").zRot = (float)Math.toRadians(-15);
				body.getChild("wing4").zRot = (float)Math.toRadians(-15);
				body.getChild("translucent").getChild("tail").xRot = (float)Math.toRadians(-15);
			}
		} else {
			body.getChild("wing1").zRot = (float)(Math.cos((ageInTicks)*1f));
			body.getChild("wing2").zRot = (float)(Math.cos(0.2+(ageInTicks)*1f));
			body.getChild("wing3").zRot = (float)(-Math.cos((ageInTicks)*1f));
			body.getChild("wing4").zRot = (float)(-Math.cos(0.2+(ageInTicks)*1f));
			body.getChild("translucent").getChild("tail").xRot = (float)(Math.cos((ageInTicks)*0.1f)*0.1f)-(float)Math.toRadians(15);
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}