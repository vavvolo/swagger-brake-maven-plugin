package io.redskap.swagger.brake.maven;

import io.redskap.swagger.brake.runner.Options;
import io.redskap.swagger.brake.runner.OutputFormat;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class OptionsFactory {
    public static Options create(RunnerParameter parameter) {
        Options options = new Options();
        options.setOldApiPath(parameter.getOldApi());
        options.setMavenRepoUrl(parameter.getMavenRepoUrl());
        options.setMavenSnapshotRepoUrl(parameter.getMavenSnapshotRepoUrl());
        options.setMavenRepoUsername(parameter.getMavenRepoUsername());
        options.setMavenRepoPassword(parameter.getMavenRepoPassword());
        options.setGroupId(parameter.getGroupId());
        options.setArtifactId(parameter.getArtifactId());
        options.setCurrentArtifactVersion(parameter.getCurrentVersion());
        options.setNewApiPath(parameter.getNewApi());
        options.setOutputFilePath(parameter.getOutputFilePath());
        options.setOutputFormats(resolveOutputFormats(parameter));
        options.setDeprecatedApiDeletionAllowed(parameter.getDeprecatedApiDeletionAllowed());
        options.setBetaApiExtensionName(parameter.getBetaApiExtensionName());
        options.setApiFilename(parameter.getApiFilename());
        return options;
    }

    private static Set<OutputFormat> resolveOutputFormats(RunnerParameter parameter) {
        Collection<String> formats = parameter.getOutputFormats();
        if (CollectionUtils.isEmpty(formats)) {
            return Collections.emptySet();
        }
        return formats.stream().map(String::toUpperCase).map(OutputFormat::valueOf).collect(toSet());
    }
}
