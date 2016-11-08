package net.blay09.mods.cookingforblockheads.client.gui;

import java.util.Comparator;
import java.util.List;

import net.blay09.mods.cookingforblockheads.api.FoodRecipeWithStatus;
import net.blay09.mods.cookingforblockheads.api.ICustomSortButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;

import com.google.common.collect.Lists;

public class GuiButtonSortCustom extends GuiButton {

    private final ICustomSortButton button;

    private final List<String> tooltipLines = Lists.newArrayList();

    public GuiButtonSortCustom(int buttonId, int x, int y, ICustomSortButton button) {
        super(buttonId, x, y, 20, 20, "");
        this.button = button;
        this.tooltipLines.add(I18n.format(this.button.getTooltip()));
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;

        int texY = button.getIconTextureY();
        if(!enabled) {
            texY += 40;
        } else if(hovered) {
            texY += 20;
        }
        GlStateManager.color(1f, 1f, 1f, 1f);
        mc.getTextureManager().bindTexture(this.button.getIcon());
        drawTexturedModalRect(xPosition, yPosition, button.getIconTextureX(), texY, width, height);
    }

    public List<String> getTooltipLines() {
        return tooltipLines;
    }
    
    public Comparator<FoodRecipeWithStatus> getComparator() {
        return button.getComparator();
    }
}
