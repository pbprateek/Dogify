//
//  ContentView.swift
//  iosApp
//
//  Created by Prateek Kumar on 25/05/23.
//

import SwiftUI
import shared


let gradient = LinearGradient(
    colors: [
        Color.black.opacity(0.6),
        Color.black.opacity(0.6),
        Color.black.opacity(0.5),
        Color.black.opacity(0.3),
        Color.black.opacity(0.0),
    ],
    startPoint: .top, endPoint: .bottom
)

struct ComposeView: UIViewControllerRepresentable {
    
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {
        
    }
    
    func makeUIViewController(context: Context) -> some UIViewController {
        
        let controller = IosMainKt.IosMainApp()
        controller.overrideUserInterfaceStyle = .light
        return controller
    }
    
}

struct ContentView: View {
    var body: some View {
        ZStack(alignment: Alignment(horizontal: .leading, vertical: .top)) {
            Color.black
                .ignoresSafeArea()
                .frame(height: 44)
            VStack {
                ComposeView()
                    .padding(.top, 44)
            }
            .edgesIgnoringSafeArea(.vertical)
        }
        .preferredColorScheme(.dark)
    }
}
