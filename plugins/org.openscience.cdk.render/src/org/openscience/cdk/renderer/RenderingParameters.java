/* Copyright (C) 2009  Gilleain Torrance <gilleain@users.sf.net>
 *               2009  Arvid Berg <goglepox@users.sf.net>
 *               2009  Egon Willighagen <egonw@users.sf.net>
 *               2009  Stefan Kuhn <shk3@users.sf.net>
 *
 * Contact: cdk-devel@list.sourceforge.net
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package org.openscience.cdk.renderer;

import java.awt.Color;

/**
 * @cdk.module render
 * @cdk.githash
 */
public class RenderingParameters {

    private boolean fitToScreen = false;

    /**
     * The maximum distance on the screen the mouse pointer has to be to
     * highlight an element.
     */
    private double highlightDistance = 8;

    private boolean highlightShapeFilled = false;

    private Color selectedPartColor = Color.lightGray;

    /**
     * The shape to display over selected atoms
     */
//    private AtomShape selectionShape = AtomShape.SQUARE;

    /**
     * The radius on screen of the selection shape
     */
    private double selectionRadius = 3;

    private boolean showAtomTypeNames = false;

    private boolean showMoleculeTitle = false;

    private boolean showTooltip = false;

    private boolean willDrawNumbers = false;

    /**
     * The width on screen of the fat end of a wedge bond.
     */
    private double wedgeWidth = 2.0;

    public boolean isHighlightShapeFilled() {
        return highlightShapeFilled;
    }

    public void setHighlightShapeFilled(boolean highlightShapeFilled) {
        this.highlightShapeFilled = highlightShapeFilled;
    }

    public double getWedgeWidth() {
        return wedgeWidth;
    }

    public void setWedgeWidth(double wedgeWidth) {
        this.wedgeWidth = wedgeWidth;
    }

    public double getHighlightDistance() {
        return highlightDistance;
    }

    public void setHighlightDistance(double highlightDistance) {
        this.highlightDistance = highlightDistance;
    }

    public boolean isFitToScreen() {
        return fitToScreen;
    }

    public void setFitToScreen(boolean fitToScreen) {
        this.fitToScreen = fitToScreen;
    }

    public Color getSelectedPartColor() {
        return selectedPartColor;
    }

    public boolean isShowAtomTypeNames() {
        return showAtomTypeNames;
    }

    public boolean isShowMoleculeTitle() {
        return showMoleculeTitle;
    }

    public boolean isShowTooltip() {
        return showTooltip;
    }

    public boolean isWillDrawNumbers() {
        return willDrawNumbers;
    }

    public void setSelectedPartColor(Color selectedPartColor) {
        this.selectedPartColor = selectedPartColor;
    }

    public void setShowAtomTypeNames(boolean showAtomTypeNames) {
        this.showAtomTypeNames = showAtomTypeNames;
    }

    public void setShowMoleculeTitle(boolean showMoleculeTitle) {
        this.showMoleculeTitle = showMoleculeTitle;
    }

    public void setShowTooltip(boolean showTooltip) {
        this.showTooltip = showTooltip;
    }

    public void setWillDrawNumbers(boolean willDrawNumbers) {
        this.willDrawNumbers = willDrawNumbers;
    }

	public double getSelectionRadius() {
		return this.selectionRadius;
	}

	public void setSelectionRadius(double selectionRadius) {
		this.selectionRadius = selectionRadius;
	}

}
