package org.ageuxo.lightenup;

import net.fabricmc.api.ModInitializer;

public class LightenUpFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {

        LightenUp.init();
    }
}
