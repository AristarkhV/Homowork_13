package mateacademy.homework.homework_13;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.IOException;
import java.util.Objects;

public class DeserializeAnimalApp {

    private static Animal[] deserializeAnimalArray(byte[] data) {
        Animal[] animals;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data))) {
            int numberOfAnimals = objectInputStream.readInt();
            animals = new Animal[numberOfAnimals];
            for (int i = 0; i < numberOfAnimals; i++) {
                animals[i] = (Animal) objectInputStream.readObject();
            }
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            throw new java.lang.IllegalArgumentException();
        }
        return animals;
    }

    private static class Animal implements Serializable {
        private final String name;

        public Animal(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            return Objects.equals(name, ((Animal) obj).name);
        }

        @Override
        public String toString() {
            return '{' + name + '}';
        }
    }

    public static void main(String[] args) {
        Animal wombat = new Animal("wombat");
        Animal crow = new Animal("crow");
        byte[] data = new byte[3];
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeInt(2);
            objectOutputStream.writeObject(wombat);
            objectOutputStream.writeObject(crow);
            data = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Animal[] animals = deserializeAnimalArray(data);
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }
}
