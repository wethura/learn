package com.wethura.base.jvm;

import org.junit.Assert;
import org.junit.Test;

import java.lang.ref.WeakReference;

public class WeakReferenceTests {

    class ReleaseResource {
    }

    class Leak {
        ReleaseResource releaseResource;

        public Leak(ReleaseResource releaseResource) {
            this.releaseResource = releaseResource;
        }

        public ReleaseResource getReleaseResource() {
            return releaseResource;
        }
    }

    @Test
    public void testMemoryLeak(){
        ReleaseResource releaseResource = new ReleaseResource();
        Leak leak = new Leak(releaseResource);
        // set to null for release memory of releaseResource.
        releaseResource = null;
        System.gc();
        Assert.assertNotNull(leak.getReleaseResource()); // expected null but not null.
    }

    class NotLeak {
        WeakReference<ReleaseResource> aRef;

        public NotLeak(ReleaseResource releaseResource) {
            this.aRef = new WeakReference<>(releaseResource);
        }

        public ReleaseResource getReleaseResource() {
            return aRef.get();
        }
    }

    @Test
    public void testMemoryReleaseAfterSetNull() {
        ReleaseResource releaseResource = new ReleaseResource();
        NotLeak notLeak = new NotLeak(releaseResource);
        releaseResource = null;

        System.gc();
        Assert.assertNull(notLeak.getReleaseResource());
    }
}
