package study.serializable;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

import org.junit.jupiter.api.Test;

class RiceCakeTest {

	@Test
	void 직렬화_역직렬화() throws IOException, ClassNotFoundException {
		//given
		RiceCake riceCake = new RiceCake("white",400,3000);

		//when
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
		objectOutputStream.writeObject(riceCake);
		byte[] convertedObject = byteArrayOutputStream.toByteArray();
		System.out.println(new String(convertedObject));
		System.out.println(Base64.getEncoder().encodeToString(convertedObject));

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(convertedObject);
		ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
		RiceCake deserializedRiceCake = (RiceCake)objectInputStream.readObject();
		System.out.println(deserializedRiceCake);

		//then
		assertThat(riceCake).isEqualTo(deserializedRiceCake);
	}
}
