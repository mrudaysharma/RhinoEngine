/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rhino.factory;

import org.rhino.factory.data.DataEmptyState;
import org.rhino.factory.data.DataDrivenInterface;
import org.rhino.factory.data.DataIfState;
import org.rhino.factory.data.DataForState;

/**
 *
 * @author u.sharma
 * Returns the ojbect of DataDrivernFactories such as DATAIF,DATAFOR and DATAEMPTYCHILD
 */
public enum AttributeTypeEnum {

    IF_ATTRIBUTE("data-if"),
    FOR_ATTRIBUTE("data-for"),
    EMPTY_ATTRIBUTE("empty-attribute");

    private AttributeTypeEnum(String attributeName) {
        this.attributeName = attributeName;
    }

    public String toString() {
        return attributeName;
    }

    public DataDrivenInterface get() {
        switch (this) {
            case IF_ATTRIBUTE:
                return new DataIfState();
            case FOR_ATTRIBUTE:
                return new DataForState();
            case EMPTY_ATTRIBUTE:
                return new DataEmptyState();
            default:
                return null;
        }
    }

    private String attributeName;
}
