import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * 初始化器，channel注册后，执行里面的相应的初始化方法
 */
public class HelloServerInitialzer extends ChannelInitializer<SocketChannel> {


	@Override
	protected void initChannel(SocketChannel socketChannel) throws Exception {

		//通过channel获取相应的pipeline
		ChannelPipeline pipeline = socketChannel.pipeline();

		//添加handler
		pipeline.addLast("HttpServerCodec", new HttpServerCodec())
				//添加自定义handler
				.addLast("customHandler", new CustomHandler());
	}
}
