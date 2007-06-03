/*
 * SpaltenUeberschrift.java
 *
 * Created on 30. Oktober 2006, 18:15
 *
 * webplanme __VERSION__ (c) Markus Schorpp
 */

package org.schorpp.planmich.web.jsf.liquiplan;

/**
 *
 * @author Markus
 */
public class SpaltenUeberschrift
        
{
    public SpaltenUeberschrift() {
    }
    
    private String _label;
    
    private String _width;
    
    private String _textAlign;
    
    private boolean _editable;
    
    public SpaltenUeberschrift(String label, String width, String textAlign, boolean editable) {
        _label = label;
        _width = width;
        _editable = editable;
        _textAlign = textAlign;
    }
    
    // =========================================================================
    // Getters
    // =========================================================================
    
    public String getLabel() {
        return _label;
    }
    
    public String getWidth() {
        return _width;
    }
    
    public String getTextAlign() {
        return _textAlign;
    }
    
    public boolean isEditable() {
        return _editable;
    }
    
    // =========================================================================
    // Getters
    // =========================================================================
    
    public void setLabel(String label) {
        _label = label;
    }
    
    public void setWidth(String width) {
        _width = width;
    }
    
    public void setEditable(boolean editable) {
        _editable = editable;
    }
    
}

