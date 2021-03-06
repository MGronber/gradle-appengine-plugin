package com.google.gcloud.task

import com.google.appengine.AppEnginePlugin
import com.google.gcloud.GCloudPluginExtension
import com.google.gcloud.wrapper.GCloudCommandBuilder
import org.gradle.api.Incubating

/**
 * Task to Deploy App Engine projects
 */
@Incubating
class GCloudAppDeployTask extends GCloudTask {

    protected String[] getCommand() {
        GCloudPluginExtension gcEx = project.extensions.getByName("gcloud")
        GCloudCommandBuilder builder = new GCloudCommandBuilder("preview", "app", "deploy")
        addBaseCommandOptions(builder);
        builder.addOption("docker-host", gcEx.app.dockerHost)
               .addOption("version", gcEx.app.version)
               .addOption("server", gcEx.app.server)
               .addBoolOption("force", gcEx.app.forceDeploy)
               .add(gcEx.app.extraParams)
               .add(AppEnginePlugin.getExplodedAppDirectory(project).absolutePath)

        return builder.buildCommand()
    }
}
