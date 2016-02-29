package com.ctp.neo4j.ogm.resourceresolver;

import java.io.File;
import java.net.URL;

import org.jboss.vfs.VFS;
import org.jboss.vfs.VirtualFile;
import org.neo4j.ogm.classloader.ResourceResolver;

public class VfsResourceResolver implements ResourceResolver {

    @Override
    public File resolve(URL url) throws Exception {
        if ("vfs".equalsIgnoreCase(url.getProtocol())) {
            VirtualFile file = VFS.getChild(url.toURI());
            return file.getPhysicalFile();
        }
        return null;
    }

}
