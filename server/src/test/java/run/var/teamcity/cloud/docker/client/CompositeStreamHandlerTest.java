package run.var.teamcity.cloud.docker.client;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class CompositeStreamHandlerTest extends StreamHandlerTest {

    public void singleStreamFragment() throws IOException {
        StreamHandler handler = createHandler();
        assertThat(handler.getNextStreamFragment()).isNotNull();
        assertThat(handler.getNextStreamFragment()).isNull();
    }

    public void noStdioType() throws IOException {
        //noinspection ConstantConditions
        assertThat(createHandler().getNextStreamFragment().getType()).isNull();
    }

    public void closeFragment() throws IOException {
        //noinspection ConstantConditions
        createHandler().getNextStreamFragment().close();
        assertThat(inputStream.isClosed()).isTrue();
    }

    @Override
    protected CompositeStreamHandler createHandler() {
        return new CompositeStreamHandler(closeHandle, inputStream, outputStream);
    }
}
