/* Copyright (C) 2009  Gilleain Torrance <gilleain@users.sf.net>
 *               2010  Egon Willighagen <egonw@users.sf.net>
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

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.renderer.RendererModel;
import org.openscience.cdk.renderer.elements.ElementGroup;
import org.openscience.cdk.renderer.elements.IRenderingElement;
import org.openscience.cdk.renderer.elements.OvalElement;
import org.openscience.cdk.renderer.generators.BasicSceneGenerator.Scale;
import org.openscience.cdk.renderer.generators.parameter.AbstractGeneratorParameter;

/**
 * @cdk.module rendercontrol
 */
public class HighlightAtomGenerator extends BasicAtomGenerator 
                                implements IGenerator<IAtomContainer> {

	/**
	 * The color used for drawing the part we are hovering over.
	 */
    public static class HoverOverColor extends
    AbstractGeneratorParameter<Color> {
    	public Color getDefault() {
    		return Color.lightGray;
    	}
    }
    private IGeneratorParameter<Color> hoverOverColor =
    	new HoverOverColor();
    
    /**
     * The maximum distance on the screen the mouse pointer has to be to
     * highlight an atom.
     */
    public static class HighlightAtomDistance extends 
                        AbstractGeneratorParameter<Double> {
        public Double getDefault() {
            return 8.0;
        }
    }
    private HighlightAtomDistance highlightAtomDistance =
    	new HighlightAtomDistance();

    public static class HighlightAtomShapeFilled extends 
                        AbstractGeneratorParameter<Boolean> {
        public Boolean getDefault() {
            return Boolean.FALSE;
        }
    }
    private HighlightAtomShapeFilled highlightAtomShapeFilled =
    	new HighlightAtomShapeFilled();

    public HighlightAtomGenerator() {}
    
    private boolean shouldHighlight(IAtom atom, RendererModel model) {
        return !super.isHydrogen(atom) || model.getParameter(
			BasicAtomGenerator.ShowExplicitHydrogens.class
		).getValue();
    }

    public IRenderingElement generate(IAtomContainer ac, RendererModel model) {
        IAtom atom = model.getHighlightedAtom();
        if (atom != null && shouldHighlight(atom, model)) {
            Point2d p = atom.getPoint2d();
            
            // the element size has to be scaled to model space 
            // so that it can be scaled back to screen space...
            double radius = 
               model.getParameter(HighlightAtomDistance.class).getValue() /
                            model.getParameter(Scale.class).getValue();
            boolean filled = 
                model.getParameter(
                        HighlightAtomShapeFilled.class).getValue();
            Color highlightColor = hoverOverColor.getValue(); 
            return new OvalElement(p.x, p.y, radius, filled, highlightColor);
        }
        
        return new ElementGroup();
    }
    
    public List<IGeneratorParameter<?>> getParameters() {
    	List<IGeneratorParameter<?>> parameters =
    		new ArrayList<IGeneratorParameter<?>>();
    	parameters.add(hoverOverColor);
    	parameters.add(highlightAtomDistance);
    	parameters.add(highlightAtomShapeFilled);
    	parameters.addAll(super.getParameters());
    	return parameters;
    }
}
