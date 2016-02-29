package com.ctp.neo4j.ogm.resourceresolver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;

import org.apache.commons.io.IOUtils;
import org.jboss.vfs.VFS;
import org.jboss.vfs.VirtualFile;
import org.junit.Test;
import org.neo4j.ogm.service.ResourceService;

public class VfsResourceResolverTest {

    @Test
    public void should_load_from_vfs() throws Exception {
        // given
        VirtualFile testFile = VFS.getChild(classpathURI("vfs/content/test.txt"));
        assertEquals("This should be a VFS file", "vfs", testFile.toURL().getProtocol());

        // when
        File file = ResourceService.resolve(testFile.toURL());

        // then
        assertNotNull(file);
        String content = IOUtils.toString(new FileInputStream(file));
        assertEquals("All work and no play makes Jack a dull boy", content);
    }

    private URI classpathURI(String resource) throws Exception {
        return Thread.currentThread().getContextClassLoader().getResource(resource).toURI();
    }
}
