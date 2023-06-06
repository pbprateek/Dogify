//
//  iosAppApp.swift
//  iosApp
//
//  Created by Prateek Kumar on 25/05/23.
//

import SwiftUI
import shared

@main
struct iosAppApp: App {
    var body: some Scene {
        
        WindowGroup {
            //This is important to ignore overlapping and all
            ContentView().ignoresSafeArea(.all)
        }
    }
}
