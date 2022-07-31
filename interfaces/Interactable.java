package interfaces;

import components.Range;

public interface Interactable{

    public void setIneractionObject(Interaction<?> interaction);

    public void setRange(Range aRange);

    public void setRangeXOffset(float anOffset);

    public void setRangeYOffset(float anOffset);

    public Range getRange();

}