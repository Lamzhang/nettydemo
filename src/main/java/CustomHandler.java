import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

public class CustomHandler extends SimpleChannelInboundHandler<HttpObject> {

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channel注册");
		super.channelRegistered(ctx);
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channel移除");
		super.channelUnregistered(ctx);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channel活跃");
		super.channelActive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channel不活跃");
		super.channelInactive(ctx);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channel读完成");
		super.channelReadComplete(ctx);
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		System.out.println("用户事件触发");
		super.userEventTriggered(ctx, evt);
	}

	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channel可写更改");
		super.channelWritabilityChanged(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("捕获channel异常");
		super.exceptionCaught(ctx, cause);
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		System.out.println("handler添加");
		super.handlerAdded(ctx);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		System.out.println("handler移除");
		super.handlerRemoved(ctx);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {

		//get channel
		Channel channel = channelHandlerContext.channel();

		if (httpObject instanceof HttpRequest) {
			//客户端远程地址
			System.out.println(channel.remoteAddress());
			//发送数据消息
			ByteBuf content = Unpooled.copiedBuffer("hello netty", CharsetUtil.UTF_8);
			//构建response
			FullHttpResponse response =
					new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
							HttpResponseStatus.OK,
							content);

			//响应的数据类型
			response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
			//响应的长度
			response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

			channelHandlerContext.writeAndFlush(response);
		}
	}
}
