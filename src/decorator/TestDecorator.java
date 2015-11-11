package decorator;

public class TestDecorator {
	public static void main(String[] args) {
		IPacketCreator pc = new PackectHttpHeaderCreator(new PacketHTMLCreator(new PacketBodyCreator()));
		System.out.println(pc.handlerContent());
	}
}
