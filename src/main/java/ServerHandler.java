import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import statistic.StatisticHolder;
import view.View;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpResponseStatus.FOUND;
import static io.netty.handler.codec.http.HttpHeaders.Names.LOCATION;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    public ServerHandler(){

    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        StatisticHolder.INSTANCE.addActiveConnection();
        System.out.println("channel is active");
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)  {
        StatisticHolder.INSTANCE.addRequest();
        if (msg instanceof HttpRequest) {
            QueryStringDecoder urlDecoder = new QueryStringDecoder(((HttpRequest) msg).getUri());
            String url = urlDecoder.path().toLowerCase();

            if (url.equals("/hello")) {
                HttpResponse res = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.copiedBuffer(View.getHelloPage(), CharsetUtil.UTF_8));
                ctx.write(res).addListener(ChannelFutureListener.CLOSE);
            }

            if (url.equals("/redirect")) {
                FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, FOUND);
                response.headers().set(LOCATION, urlDecoder.parameters().get("url").get(0));
                ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                StatisticHolder.INSTANCE.addRedirection(urlDecoder.parameters().get("url").get(0));
            }

            if (url.equals("/status")) {
                HttpResponse res = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.copiedBuffer(View.getStatisticPage(), CharsetUtil.UTF_8));
                ctx.write(res).addListener(ChannelFutureListener.CLOSE);
            }




        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
        StatisticHolder.INSTANCE.removeActiveConnection();
    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
} 