package study.nested;

public class House {

	private Room room;

	public House(int roomNo) {
		this.room = new Room(roomNo);
		System.out.println(room.roomNo);
		System.out.println(room.getRoomNo());
	}

	public int getRoomNo(){
		return room.getRoomNo();
	}

	private class Room{
		private int roomNo;

		private Room(int roomNo) {
			this.roomNo = roomNo;
		}

		private int getRoomNo() {
			return roomNo;
		}

		private void setRoomNo(int roomNo) {
			this.roomNo = roomNo;
		}
	}

}
