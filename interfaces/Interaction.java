package interfaces;

import gameObjects.Entity;
import gameObjects.InteractableStationary;

public interface Interaction<T extends Entity & Interactable> {

    public void apply(InteractableStationary interactableStationary);

}
