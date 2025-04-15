package blueduck.outer_end.client.model;// Made with Blockbench 4.9.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import blueduck.outer_end.entity.Himmelite;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

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
        float pct = ageInTicks - entity.tickCount;

        // the getChild spam bothered me - GiantLuigi4
        ModelPart hips = body.getChild("hips");
        ModelPart referenceLeg = hips.getChild("frontrightleg");
        ModelPart head = hips.getChild("head");
        ModelPart upjaw = head.getChild("upjaw");
        ModelPart downjaw = head.getChild("downjaw");

        // animate limbs
        referenceLeg.xRot = (float) (Math.sin(limbSwing / 2) * limbSwingAmount);
        hips.getChild("frontleftleg").xRot = -referenceLeg.xRot;
        hips.getChild("backrightleg").xRot = -referenceLeg.xRot;
        hips.getChild("backleftleg").xRot = referenceLeg.xRot;

        if (entity.getBiteFactor() >= 1) {
            // animate chomp
            int start = entity.getLastBiteFactor();
            int end = entity.getBiteFactor();
            if (end == entity.getLastBiteFactor()) {
                if (entity.getBiteFactor() <= 15)
                    end += 1;

                if (end > 17) { // >17 is drawn as default state
                    start = 0;
                    end = 0;
                } else if (end == 17) // 17 is currently biting
                    end = 0;
            }

            upjaw.xRot = (float) -Math.toRadians(Mth.lerp(pct, start, end) * 2);
            entity.updateBiteFactor();
        } else {
            // animate jaws
            upjaw.xRot = -Math.abs(Mth.sin(limbSwing / 3)) * limbSwingAmount / 8f;
        }
        downjaw.xRot = -upjaw.xRot;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}