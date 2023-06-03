//
//  ContentView.swift
//  iosApp
//
//  Created by Prateek Kumar on 25/05/23.
//

import SwiftUI
import shared

struct ContentView: UIViewControllerRepresentable {
    
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {
        
    }
    
    func makeUIViewController(context: Context) -> some UIViewController {
        IosMainKt.IosMainApp()
    }

}
