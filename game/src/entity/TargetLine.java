package entity;

public class TargetLine {
	public float x1;
	public float x2;
	public float y1;
	public float y2;
	private float lineLength;
	public double angle;

	public TargetLine(float x1, float x2, float y1, float y2, int angle) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.lineLength = (float) Math.sqrt((float) (Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
		this.angle = angle;

	}

	public void increaseAngle(String direction) {

		if (angle >= 80)
			return;

		angle += 0.7;

		double angleToRadian = Math.toRadians(angle);

		if (direction == "left") {
			// System.out.println("left");
			// System.out.println(Math.sin(angleToRadian));
			x2 = x1 - (float) (Math.sin(angleToRadian) * lineLength);
			y2 = y1 + (float) (Math.cos(angleToRadian) * lineLength);
			// System.out.println(x2 + " " + y2 + " " + angle + " " + lineLength);
		}

		else if (direction == "right") {
			// System.out.println("right");
			x2 = x1 + (float) (Math.sin(angleToRadian) * lineLength);
			y2 = y1 + (float) (Math.cos(angleToRadian) * lineLength);
			// System.out.println(x2 + " " + y2 + " " + angle + " " + lineLength);
		}
	}

	public void decreaseAngle(String direction) {
		if (angle == 0)
			return;

		angle -= 0.7;

		double angleToRadian = Math.toRadians(angle);

		if (direction == "right") {
			x2 = x2 + ((float) ((Math.sin(angleToRadian) * lineLength))
					- ((float) (Math.sin(angleToRadian - Math.toRadians(0.7)) * lineLength)));
			y2 = y2 + ((float) (Math.cos(angleToRadian) * lineLength)
					- ((float) (Math.cos(angleToRadian + Math.toRadians(0.7)) * lineLength)));
			// System.out.println(x2 + " " + y2 + " " + angle + " " + lineLength);
		}

		else if (direction == "left") {
			x2 = x2 - ((float) ((Math.sin(angleToRadian) * lineLength))
					- ((float) (Math.sin(angleToRadian - Math.toRadians(0.7)) * lineLength)));
			y2 = y2 + ((float) (Math.cos(angleToRadian) * lineLength)
					- ((float) (Math.cos(angleToRadian + Math.toRadians(0.7)) * lineLength)));
			// System.out.println(x2 + " " + y2 + " " + angle + " " + lineLength);
		}

	}

}
