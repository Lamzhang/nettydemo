import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class HelloServer {

	public static void main(String[] args) throws Exception{

		//定义主从线程组
		EventLoopGroup  parentGroup = new NioEventLoopGroup();
		EventLoopGroup  childGroup = new NioEventLoopGroup();

		try {
			//netty服务器的创建，ServerBootstrap是一个启动类
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(parentGroup, childGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new HelloServerInitialzer());

			//启动server， 设置端口号，同时启动方式为同步
			ChannelFuture channelFuture = serverBootstrap.bind(8081).sync();

			//监听关闭的channel， 设置位同步方式
			channelFuture.channel().closeFuture().sync();
		}catch (Exception e) {
			parentGroup.shutdownGracefully();

			childGroup.shutdownGracefully();
		}
	}
}
