package decorator;

public class PacketBodyCreator implements IPacketCreator {

	@Override
	public String handlerContent() {
		return "this is content body";
	}
}
