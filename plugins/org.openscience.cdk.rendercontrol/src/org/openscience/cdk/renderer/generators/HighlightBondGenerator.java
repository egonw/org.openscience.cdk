/* Copyright (C) 2009  Gilleain Torrance <gilleain@users.sf.net>
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
package org.openscience.cdk.renderer.generators;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Point2d;

import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.renderer.RendererModel;
import org.openscience.cdk.renderer.elements.ElementGroup;
import org.openscience.cdk.renderer.elements.IRenderingElement;
import org.openscience.cdk.renderer.elements.OvalElement;
import org.openscience.cdk.renderer.generators.BasicSceneGenerator.Scale;
import org.openscience.cdk.renderer.generators.HighlightAtomGenerator.HoverOverColor;
import org.openscience.cdk.renderer.generators.parameter.AbstractGeneratorParameter;

/**
 * @cdk.module rendercontrol
 */
public class HighlightBondGenerator extends BasicBondGenerator 
                                    implements IGenerator<IAtomContainer> {

    /**
     * The maximum distance on the screen the mouse pointer has to be to
     * highlight a bond.
     */
    public static class HighlightBondDistance extends 
                        AbstractGeneratorParameter<Double> {
        public Double getDefault() {
            return 8.0;
        }
    }
    private HighlightBondDistance highlightBondDistance =
    	new HighlightBondDistance();
    
    public static class HighlightBondShapeFilled extends 
                        AbstractGeneratorParameter<Boolean> {
        public Boolean getDefault() {
            return Boolean.FALSE;
        }
    }
    
    private HighlightBondShapeFilled highlightBondShapeFilled =
    	new HighlightBondShapeFilled();
    
    public HighlightBondGenerator() {}
    
    private boolean shouldHighlight(IBond bond, RendererModel model) {
        return !super.bindsHydrogen(bond) || model.getParameter(
			BasicAtomGenerator.ShowExplicitHydrogens.class
		).getValue();
    }

    public IRenderingElement generate(IAtomContainer ac, RendererModel model) {
        IBond bond = model.getHighlightedBond();
        if (bond != null && shouldHighlight(bond, model)) {
            super.ringSet = super.getRingSet(ac);
            
            double r = model.getParameter(
                    HighlightBondDistance.class).getValue() /
                       model.getParameter(Scale.class).getValue();
            Color hColor = model.getParameter(HoverOverColor.class).
            	getValue();
            Point2d p = bond.get2DCenter();
            boolean filled = model.getParameter(
                    HighlightBondShapeFilled.class).getValue();
            return new OvalElement(p.x, p.y, r, filled, hColor);
        }
        return new ElementGroup();
    }
    
    public List<IGeneratorParameter<?>> getParameters() {
    	List<IGeneratorParameter<?>> parameters =
    		new ArrayList<IGeneratorParameter<?>>();
    	parameters.add(highlightBondDistance);
    	parameters.add(highlightBondShapeFilled);
    	parameters.addAll(super.getParameters());
    	return parameters;
    }
}
