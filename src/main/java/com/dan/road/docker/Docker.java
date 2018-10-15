package com.dan.road.docker;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerCertificateException;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.DockerException;
import com.spotify.docker.client.messages.Container;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dan on 2018/10/15.
 * Docker 工具类
 */
public class Docker {

    private static final String LABEL_COMPOSE_CONTAINER_NUMBER = "com.docker.compose.container-number";
    private static final String LABEL_COMPOSE_PROJECT = "com.docker.compose.project";
    private static final String LABEL_COMPOSE_SERVICE = "com.docker.compose.service";

    private DockerClient dockerClient;

    private static volatile Docker docker = null;

    public static Docker getInstance() throws DockerCertificateException {
        if (docker == null) {
            synchronized (Docker.class) {
                if (docker == null) {
                    docker = new Docker();
                }
            }
        }
        return docker;
    }

    private Docker() throws DockerCertificateException {
        dockerClient = DefaultDockerClient.fromEnv().build();
    }

    public DockerClient getClient() {
        return dockerClient;
    }

    public String getEnvHostname() {
        return System.getenv("HOSTNAME");
    }

    public Container me() throws DockerException, InterruptedException {
        return this.getClient().listContainers().stream().filter(this::filterIsIdEqualHostname).limit(1).collect(Collectors.toList()).get(0);
    }

    public int getId() throws DockerException, InterruptedException {
        return Integer.parseInt(this.me().id());
    }

    public String getService() throws DockerException, InterruptedException {
        return this.getService(this.me());
    }
    public String getService(Container container) throws DockerException, InterruptedException {
        return container.labels().get(LABEL_COMPOSE_SERVICE);
    }

    public String getName() throws DockerException, InterruptedException {
        return this.getService();
    }
    public String getName(Container container) throws DockerException, InterruptedException {
        return this.getService(container);
    }

    public String getProject() throws DockerException, InterruptedException {
        return this.getProject(this.me());
    }
    public String getProject(Container container) throws DockerException, InterruptedException {
        return container.labels().get(LABEL_COMPOSE_PROJECT);
    }

    public int getNumber() throws DockerException, InterruptedException {
        return this.getNumber(this.me());
    }
    public int getNumber(Container container) throws DockerException, InterruptedException {
        return Integer.parseInt(container.labels().get(LABEL_COMPOSE_CONTAINER_NUMBER));
    }

    public List<Container> getContainers(boolean excludeSelf) throws DockerException, InterruptedException {
        Container me = me();
        return this.getClient().listContainers(
                DockerClient.ListContainersParam.withLabel(LABEL_COMPOSE_PROJECT, this.getProject(me)),
                DockerClient.ListContainersParam.withLabel(LABEL_COMPOSE_SERVICE, this.getService(me))
        ).stream()
                .filter(c -> !excludeSelf || this.filterIsIdEqualHostname(c))
                .filter(c -> { try {
                    return this.getService(me).equals(c.labels().get(this.LABEL_COMPOSE_SERVICE));
                } catch (InterruptedException | DockerException e) {return false;}})
                //.filter(this::output)
                .collect(Collectors.toList());
    }

    public int getTotal(boolean excludeSelf) {
        try {
            return this.getContainers(false).size();
        } catch (DockerException | InterruptedException e) {
            return 0;
        }
    }

    public String getHostname() throws DockerException, InterruptedException {
        return this.getHostname(this.me());
    }

    String getHostname(Container container) throws DockerException, InterruptedException {
        return "" + this.getProject(container) + "_" + this.getService(container) + "_" + this.getNumber(container);
    }

    public List<String> getHostnames(boolean excludeSelf) throws DockerException, InterruptedException {
        //http://stackoverflow.com/a/19757456/3423324
        return this.getContainers(excludeSelf).stream().map(c -> { try { return this.getHostname(c); } catch (InterruptedException | DockerException e) { e.printStackTrace(); return null; } }).filter(c -> c != null).collect(Collectors.toList());
    }

    private boolean filterIsIdEqualHostname(Container c) {
        return c.id().substring(0, 12).equals(this.getEnvHostname().substring(0, 12));
    }

    public String getApiHost() {
        return System.getenv("API_HOST");
    }
}
