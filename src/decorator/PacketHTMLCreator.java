package decorator;

public class PacketHTMLCreator extends PacketDecorator {

	public PacketHTMLCreator(IPacketCreator c) {
		super(c);
	}

	@Override
	public String handlerContent() {
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<body>");
		sb.append(component.handlerContent());
		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();
	}

}
