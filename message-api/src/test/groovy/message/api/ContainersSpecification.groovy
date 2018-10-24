package message.api

import org.junit.ClassRule
import org.testcontainers.containers.DockerComposeContainer
import org.testcontainers.spock.Testcontainers
import spock.lang.Shared
import spock.lang.Specification

@Testcontainers
abstract class ContainersSpecification extends Specification {

    @Shared
    @ClassRule
    public static DockerComposeContainer environment =
            new DockerComposeContainer(new File("src/test/resources/docker-compose-test.yml")).withLocalCompose(true).start()

}
