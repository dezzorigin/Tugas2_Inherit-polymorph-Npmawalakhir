package soal2;

/** Kelas B untuk soal 2 bagian B. */
class B extends A {
    int nilai = 5;

    B() {
        super();
        System.out.println("B dibuat, nilai=" + nilai);
    }

    @Override
    void init() {
        System.out.println("init B, nilai=" + nilai);
    }
}
