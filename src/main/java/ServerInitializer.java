import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;


import java.time.LocalDateTime;


public class ServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel sc) throws Exception {

        ChannelPipeline cp = sc.pipeline();
        cp.addLast(new HttpRequestDecoder());
        cp.addLast(new HttpResponseEncoder());
        cp.addLast("handler", new ServerHandler());

    }
} 