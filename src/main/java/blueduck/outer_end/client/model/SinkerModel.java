package blueduck.outer_end.client.model;// Made with Blockbench 4.9.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import blueduck.outer_end.TheOuterEnd;
import blueduck.outer_end.entity.Sinker;
import net.minecraft.client.model.EntityModel;
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
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
public class SinkerModel<T extends Sinker> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(TheOuterEnd.MODID, "sinker"), "main");
	private final ModelPart root;

	public SinkerModel(ModelPart root) {
		super(RenderType::entityTranslucent);
		this.root = root.getChild("root");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition brine = root.addOrReplaceChild("brine", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition ujaw = root.addOrReplaceChild("ujaw", CubeListBuilder.create().texOffs(32, 32).addBox(-4.0F, -4.0F, -8.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 4.0F));

		PartDefinition ljaw = root.addOrReplaceChild("ljaw", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, -1.0F, -8.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 4.0F));

		PartDefinition eye = root.addOrReplaceChild("eye", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		root.getChild("brine").xRot = Mth.sin((float) (ageInTicks * 0.25 + 0.25 * limbSwingAmount));
		root.getChild("brine").zRot = Mth.cos((float) (0.125 + ageInTicks * 0.25 + 0.25 * limbSwingAmount));
		root.getChild("brine").yRot = (float) Math.toRadians(45);
		root.yRot = (float) Math.toRadians(netHeadYaw);


		root.getChild("ujaw").xRot = (float) (-0.5 * Mth.abs(Mth.sin((float) (ageInTicks * 0.125))));
		root.getChild("ljaw").xRot = (float) (0.5 * Mth.abs(Mth.sin((float) (ageInTicks * 0.125))));

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}