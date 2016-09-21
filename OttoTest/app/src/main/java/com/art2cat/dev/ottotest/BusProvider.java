package com.art2cat.dev.ottotest;

import com.squareup.otto.Bus;

/**
 * Created by art2cat
 * on 9/21/16.
 */

public final class BusProvider {

    private static final Bus BUS = new Bus();

    public static Bus getInstance() {
        return BUS;
    }

    private BusProvider() {
        // No instances.
    }
}
