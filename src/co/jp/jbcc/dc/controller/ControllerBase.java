package co.jp.jbcc.dc.controller;

public abstract class ControllerBase {

	/**
	 * 初期処理。
	 */
	protected abstract void init();

	/**
	 * 主処理。
	 */
	protected abstract void execute();

	/**
	 * 終了処理。
	 */
	protected abstract void close();

}
