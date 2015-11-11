package decorator;

public class PackectHttpHeaderCreator extends PacketDecorator {

	public PackectHttpHeaderCreator(IPacketCreator c) {
		super(c);
	}

	@Override
	public String handlerContent() {
		StringBuffer sb = new StringBuffer();
		sb.append("HTTP Header");
		sb.append(component.handlerContent());
		return sb.toString();
	}

}
