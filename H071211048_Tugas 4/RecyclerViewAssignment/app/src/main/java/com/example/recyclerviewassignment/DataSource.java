package com.example.recyclerviewassignment;

import java.util.ArrayList;

public class DataSource {

    public static ArrayList<Chat> chats = generateDummychat();

    public static ArrayList<Chat> generateDummychat(){
        ArrayList<Chat> chats = new ArrayList<>();
        chats.add(new Chat("anna","do you want to build a snowman?","06.15",R.drawable.anna, "+621234477474", "knock knock", "1 Dec 2022"));
        chats.add(new Chat("elsa","let it go, let it go","07.15",R.drawable.elsa,  "+153774839", "Queen of Arandelle", "2 Dec 2022"));
        chats.add(new Chat("rapunzel","and at last i see the light...","08.15",R.drawable.rapunzel, "+621234456789", "-", "3 Dec 2022"));
        chats.add(new Chat("mulan","when will my reflection show who I am inside?","09.15",R.drawable.mulan,  "+16728632", "Busy", "4  Dec 2022"));
        chats.add(new Chat("sofia","i was a girl in the village doing all right","10.15",R.drawable.sofia,  "+821234477474", "Sofia The First", "5 Dec 2022"));
        chats.add(new Chat("miguel","remember me though i have to say goodbye","11.15",R.drawable.coco, "+122244232", "Nothing's more important than family", "6 Dec 2022"));
        chats.add(new Chat("moana","i've been straring at the edge of the water","12.15",R.drawable.moana,  "+82124747321", "I am Moana of Motunui", "7 Dec 2022"));
        chats.add(new Chat("smurfs","la la-la-la la-la sing a happy song", "13.15",R.drawable.smurfs,  "+62361821812", "larii, ada gargamel", "8 Dec 2022"));
        chats.add(new Chat("rosita the pig","i never miss a beat. i'm lightin' on my feet","14.15",R.drawable.rosita,  "+12819281", "available", "9 Dec 2022"));
        chats.add(new Chat("minions","ba ba ba, ba ba na na", "15.15",R.drawable.minions,  "+821920399391", "Bello!", "10 Dec 2022"));
        return chats;
    }

}
